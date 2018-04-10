package Presentacion;

public abstract class Controller{

    private static ControllerImp control;

    abstract void execute(int event,Object datos);

    public static synchronized ControllerImp getInstance(){
        if(control==null)
            control=new ControllerImp();
        return control;
    }
}