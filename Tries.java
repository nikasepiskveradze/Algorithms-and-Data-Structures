class Node {
  public boolean endOfWord;
  public static final int R = 26;
  public Node[] children;

  public Node(){
    children = new Node[R];
  }
}

class Tries {
  private Node root;

  public Tries(){
    root = new Node();
  }

  // Add String To The Structure
  public void add(String s){
    Node currentNode = root;

    for(int i = 0; i < s.length(); i++){
      if(currentNode.children[s.charAt(i) - 'a'] == null){
        currentNode.children[s.charAt(i) - 'a'] = new Node();
      }

      currentNode = currentNode.children[s.charAt(i) - 'a'];
    }

    currentNode.endOfWord = true;
  }

  // Check whether string exists in the structure
  public boolean exists(String s){
    Node currentNode = root;

    for(int i = 0; i < s.length(); i++){
      if(currentNode.children[s.charAt(i) - 'a'] == null){
        return false;
      }
      currentNode = currentNode.children[s.charAt(i) - 'a'];
    }

    if(currentNode.endOfWord == true){
      return true;
    }

    return false;
  }

  // Remove any existing string in the structure
  public void delete(String s){
    if(exists(s)){
      deleteMechanism(s, 0, root);
    }
  }

  private Node deleteMechanism(String s, int index, Node currentNode){
    if(index == s.length()){
      currentNode.endOfWord = false;
      if(checkIfAnyChild(currentNode)){
        return currentNode;
      }

      return null;
    }else {
      currentNode.children[s.charAt(index) - 'a'] = deleteMechanism(s, index + 1, currentNode.children[s.charAt(index) - 'a']);

      if(checkIfAnyChild(currentNode)){
        return currentNode;
      }

      return null;
    }
  }

  private boolean checkIfAnyChild(Node n){
    for(int i = 0; i < Node.R; i++){
      if(n.children[i] != null){
        return true;
      }
    }

    return false;
  }

}

public class Main {
  public static void main(String []args){

    // Test Here This Structure

  }
}