package ss;

public class TestObject {
	
	public static void main(String[] args) {
		ObjectOne o=new ObjectOne();
		o.setName("zhangs");
		System.out.println(o.getName());
	}

}

/**
 * 单利
 * @author Administrator
 *
 */
class SingletonClass{
    private static volatile SingletonClass instance=null;
    public static SingletonClass getInstance(){
            synchronized(SingletonClass.class){
                if(instance==null){
                    instance=new SingletonClass();
                }
            }
        return instance;
    }
    private SingletonClass(){}
}
