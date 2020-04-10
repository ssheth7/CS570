
public class PairInt{
    private int x;
    private int y;
    
    public PairInt(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    public boolean equals(Object p){
        if(((PairInt) p).getX() == x && ((PairInt) p).getY() == y)
            return true;
        return false;
    }
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    public PairInt copy(){
        return new PairInt(x, y);
    }
}