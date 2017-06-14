package mysql;


//代理
public class DAOfactory {
	public static StudentDAO getStudentDAOInstance(){
		return new StudentDAOProxy();
	}
}
