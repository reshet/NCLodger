package com.nclodger.dao;

/**
 * Created with IntelliJ IDEA.
 * User: antoshka
 * Date: 08.10.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public abstract class UserDao implements UserDaoInterface
{
    //якось треба буде встановити зв'язок за допомогою цього
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public void insert(User _user)
    {
       Statement st = dataBase.createStatement();
       String sql = "INSERT INTO User(email,password,name)" +
                "values" +
                //звідки їх взяти?
        //поки так - треба буде створити сутність для користувача

        jdbcTemplate.update(sql, getPreparedStatementSetter(_user));
    }

}
