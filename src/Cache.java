import java.util.LinkedList;

// 50` 09``
// LRU (Least Recently Used)

public class Cache {
  static int cacheHit = 1;
  static int cacheMiss = 5;

  private String[] cache;
  private int cacheSize;
  private int totalTime;
  private LinkedList<Integer> indexList;

  public Cache(int cacheSize){
    this.cacheSize = cacheSize;
    this.cache = new String[cacheSize];
    this.indexList = new LinkedList<Integer>();
    this.totalTime = 0;

    init();
  }

  private void init(){
    for(int i=0; i<this.cacheSize; i++) {
    	this.cache[i] = "";
    	this.indexList.add(i); 
    }
  }

  private int searchCache(String str){
    for(int i=0; i<this.cacheSize; i++){
      if(this.cache[i].equals(str))
        return i;
    }
    return -1;
  }

//  First -> Last 
  private void sortIndexList(int target){
    for(int i=0; i<this.cacheSize; i++){
      if (this.indexList.get(i) == target) {
        this.indexList.remove(i);
        break;
      }
    }
    this.indexList.add(target);
  }

  public void addCache(String str){
	if(this.cacheSize<1) {
		this.totalTime += cacheMiss;
	} else {
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
  }

  public int getTotalTime(){ return this.totalTime; }

  public static void main (String[] args){
    int cacheSize = 2;
    String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};

    Cache c = new Cache(cacheSize);
    for(int i=0; i<cities.length; i++){
      c.addCache(cities[i].toLowerCase());		
    }

    System.out.println(c.getTotalTime());
  }
}
