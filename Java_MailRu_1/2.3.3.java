Задание: 
interface A {

            int sum(int x, int y);

}

class B implements A{

            public int diff(int x, int y){return x - y;}

            public int sum(int x, int y){return x + y;}

}

class C extends B{

            public int mult(int x, int y){return x * y;}

            public int diff(int x, int y){return y - x;}

}

 

A aB = new B();

A aC = new C();

B bB= new B();

B bC= new C();

C cC = new C();

Отметьте выражения которые могут быть скомпиллированы и результат вычисления которых true

aB.diff(1,1) == 0;
aC.sum(1,1)==2; +
bC.diff(1,2) ==1; +
bB.diff(1,2)==1;
bC.mult(2,2)==4;

Use : 
http://www.browxy.com/

Test: 
interface A {

            int sum(int x, int y);

}

class Id
{
	public static void main (String[] args) throws java.lang.Exception
	{
	
	class B implements A{

            public int diff(int x, int y){return x - y;}

            public int sum(int x, int y){return x + y;}

}
class C extends B{

            public int mult(int x, int y){return x * y;}

            public int diff(int x, int y){return y - x;}

}
	
A aB = new B();

A aC = new C();

B bB= new B();

B bC= new C();

C cC = new C();

//aB.diff(1,1);
//System.out.println(bB.diff(1,2)); //-1
System.out.println(bC.diff(1,2)); //1 
//bC.mult(2,2);
System.out.println(aC.sum(1,1)); //2
}

}
