package com.tuling.designpattern.abstractfactroy;

/**
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {

        IDBComponent idbComponent=new OralceDbComponent(); // config.

        IConnection connection=idbComponent.getConnection();
        connection.connection();

        ICommand command=idbComponent.getCommand();
        command.command();

    }
}
//  变化:        mysql , sqlserver , oracle ........
//  c/s(稳定):   connection, command, ......

interface IConnection{

    void connection();
}

interface ICommand{

    void command();
}


interface IDBComponent{

    IConnection getConnection();

    ICommand getCommand();
}

// ------------------

class MyConnection implements IConnection{

    @Override
    public void connection() {
        System.out.println("mysql: connect.");
    }
}

class MyCommand implements ICommand{

    @Override
    public void command() {
        System.out.println("mysql: command.");
    }
}

class MysqlDbComponent implements IDBComponent{

    @Override
    public IConnection getConnection() {
        return new MyConnection();
    }

    @Override
    public ICommand getCommand() {
        return new MyCommand();
    }
}
//--------oracle

class OracleConnection implements IConnection{


    @Override
    public void connection() {
        System.out.println("oracle:connect.");
    }
}

class OracleCommand implements ICommand{

    @Override
    public void command() {
        System.out.println("oracle:command.;");
    }
}

class OralceDbComponent implements IDBComponent{

    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}















