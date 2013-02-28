package cn.edu.nju.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-2-28
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
    public static SessionFactory session_factory;

    public static final ThreadLocal session=new ThreadLocal();
    public static Session currentSession() throws HibernateException {
        Session s= (Session) session.get();
        if(s==null){
            s = session_factory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException{
        Session s= (Session) session.get();
        if(s!=null){
            s.close();
        }
        session.set(null);
    }

    public SessionFactory getSession_factory() {
        return session_factory;
    }

    public void setSession_factory(SessionFactory session_factory) {
        HibernateUtil.session_factory = session_factory;
    }
}
