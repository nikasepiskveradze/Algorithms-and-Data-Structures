class CompleteSearch {

  // Print 
  private void print(int[] set){
    for(int i = 0; i < set.length; i++){
      System.out.println(set[i]);
    }
  }

  private void print(int[] set, boolean[] includes){
    for(int i = 0; i < includes.length; i++){
      if(includes[i]){
        System.out.println(set[i]);
      }
    }
  }

  // Swap 
  private void swap(int[] set, int a, int b){
    int tmp = set[a];
    set[a] = set[b];
    set[b] = tmp;
  }

  // Subset Finding Recursive And Iterative
  public void subSetFind(int[] set, int index, boolean[] includes){
    if(index == set.length){
      print(set, includes);
    }else {
      subSetFind(set, index + 1, includes);
      includes[index] = true;
      subSetFind(set, index + 1, includes);
    }
  }

  public void subSetFind(int[] set){
    int n = (1 << set.length);

    for(int i = 0; i < n; i++){

      for(int j = 0; j < 32; j++){
        if((i & (1 << j)) != 0){
          print(set[j]);
        }
      }
      print('\n');
    }
  }

  // Find Permutations
  public void permutationFind(int[] set, int index){
    if(index == set.length){
      print(set);
    }else {
      for(int i = index; i < set.length; i++){
        swap(set, index, i);
        permutationFind(set, index + 1);
        swap(set, index, i);
      }
    }
  }
  
}

public class Main {
  public static void main(String []args){

    // Test Here This 

  }
}