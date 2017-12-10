package statistics.matcher;

import statistics.Player;

public class QueryBuilder {
    Matcher matcher;



    public QueryBuilder(){
        matcher = new And();
    }

    public Matcher build(){
        Matcher toReturn = this.matcher;
        this.matcher = new And();
        return toReturn;
    }

    public QueryBuilder playsIn(String team){
        matcher = new And(matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int i, String category){
        matcher = new And(matcher, new HasAtLeast(i, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int i, String category){
        matcher = new And(matcher, new HasFewerThan(i, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2){
        matcher = new Or(m1, m2);
        return this;
    }

    public QueryBuilder noneOf(Matcher m1, Matcher m2){
        matcher = new Not(m1, m2);
        return this;
    }
}

