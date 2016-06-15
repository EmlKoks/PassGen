import java.util.Arrays;
import java.util.Random;

public class PassGen {
	private static boolean lowercase=false;
	private static boolean uppercase=false;
	private static boolean numbers=false;
	private static boolean specChars=false;
	private static int length=8;
	private static int n=10;
	private static char[][] passwords;

	public static void main(String[] args) {
		if(args.length>0){
			if("/?".equals(args[0]) || "--help".equals(args[0]) || "help".equals(args[0])){
				System.out.println("Help\n");
				System.out.println("-l=x\tLength passwords(Defalut l=8)");
				System.out.println("-n=x\tNumber of generated passwords(Defalut n=10)\n");
				System.out.println("-a\tLower case");
				System.out.println("-A\tUpper case");
				System.out.println("-0\tNumbers");
				System.out.println("-!\tSpecial characters");
				return;
			}
			for(String arg: args){
				if(arg.charAt(0)=='-'){
					switch(arg.charAt(1)){
						case 'l':
							try{
								if(arg.charAt(2)=='=') length=Integer.parseInt(arg.substring(3));
								else throw new Exception();
								if(length<1) throw new Exception();
							}catch(Exception e){
								System.out.println("Wrong length passwords");
							}
							break;
						case 'n':
							try{
								if(arg.charAt(2)=='=') n=Integer.parseInt(arg.substring(3));
								else throw new Exception();
								if(n<1) throw new Exception();
							}catch(Exception e){
								System.out.println("Wrong number of generated passwords");
							}
							break;
						case 'a':
							lowercase=true;
							break;
						case 'A':
							uppercase=true;
							break;
						case '0':
							numbers=true;
							break;
						case '!':
							specChars=true;
							break;
						default:
							break;
					}
				}
			}
		}	
		generatePass();	
		for(char[] i:passwords){
			for(char j:i) System.out.print(j);
			System.out.println();
		}
		
	}	
	private static void generatePass(){
		if(!lowercase && !uppercase && !numbers && !specChars) lowercase=true;
		int nCharType=0;
		if(lowercase) ++nCharType;
		if(uppercase) ++nCharType;
		if(numbers) ++nCharType;
		if(specChars) ++nCharType;		
		int pom=(int)length/nCharType;		
		int[] c = new int[4];
		Random rand = new Random();
		int a;
		if(lowercase) c[0]=pom;
		if(uppercase) c[1]=pom;
		if(numbers) c[2]=pom;
		if(specChars) c[3]=pom;	
		while(c[0]+c[1]+c[2]+c[3]<length){
			a=rand.nextInt(4);
			if(c[a]>0) ++c[a];
		}
		passwords = new char[n][length];
		for(int i=0 ; i<n ; ++i){
			int[] temp = Arrays.copyOf(c, 4);
			for(int j=0 ; j<length ; ++j){
				while(true){
					a=rand.nextInt(4);
					if(temp[a]>0){
						if(a==0){
							passwords[i][j]=(char)(rand.nextInt(26)+'a');
							--temp[a];
							break;
						}
						if(a==1){
							passwords[i][j]=(char)(rand.nextInt(26)+'A');
							--temp[a];
							break;
						}
						if(a==2){
							passwords[i][j]=(char)(rand.nextInt(10)+'0');
							--temp[a];
							break;
						}
						if(a==3){
							passwords[i][j]=(char)(rand.nextInt(15)+'!');
							--temp[a];
							break;
						}
					}
				}
				
			}
		}
		
	}
}
