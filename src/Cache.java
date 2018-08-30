import java.util.LinkedList;

public class Cache {
  static int cacheHit = 1;
  static int cacheMiss = 1;

  private String[] cache;
  private int cacheSize;
  private int totalTime;
  private LinkedList<Integer> indexList;

  public Cache(int cacheSize){
    this.cacheSize = cacheSize;
    this.cache = new String[cacheSize];
    this.isHit = new boolean[cacheSize];
    this.indexList = new LinkedList<Integer>();
    this.totalTime = 0;

    initIndexList();
  }

  private void initIndexList(){
    for(int i=0; i<this.cacheSize; i++)
      this.indexList.add(i);
  }

  private int searchCache(String str){
    for(int i=0; i<this.cacheSize; i++){
      if(this.cache[i].equals(str))
        return i;
    }
    return -1;
  }

// LinkedList가 for 돌 때 접근 방법
  private void sortIndexList(int target){
    for(int i=0; i<this.cacheSize; i++){
      if (this.indexList.get(i) == target)
        this.indexList.remove(i);
    }
    this.indexList.add(target);
  }

  public void addCache(String str){
    int idx = searchCache(str);

    if(idx == -1){
      int curIdx = this.indexList.remove(0);
      this.cache[curIdx] = str;
      this.indexList.add(curIdx);
      this.totalTime += cacheMiss;
    } else {
      sortIndexList(idx);
      this.totalTime += cacheHit;
    }
  }

  public int getTotalTime(){ return this.totalTime; }

  public static void main (String[] args){
    int cacheSize = 3;
    String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

    Cache c = new Cache(cacheSize);
    for(int i=0; i<cities.length(); i++){
      c.addCache(cities[i]);
    }

    System.out.println(c.getTotalTime());
  }
}
