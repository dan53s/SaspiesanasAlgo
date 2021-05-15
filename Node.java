public class Node{
 // Mezgla klase, tiek izmantota Huffman mezglu inicializācijai un mezglu salīdzināšanai pēc to biežuma.
  public Node leftNode, rightNode;
  public int weight, character;
  // Mezgla inicializācija
  public Node(int weight){
    this.weight = weight;
  };
  public Node(int weight, char character){
      this.weight = weight;
      this.character = character;
  };
  public Node(int weight, Node leftNode, Node rightNode){
      this.weight = weight;
      this.leftNode = leftNode;
      this.rightNode = rightNode;
  };
  public void setWeight(int weight){
    this.weight = weight;
  }

  public int getWeight(){
    return this.weight;
  }

  public void setChar(char character){
    this.character = character;
  }

  public int getChar(){
    return this.character;
  }

  public void setLeftNode(Node leftNode){
    this.leftNode = leftNode;
  }

  public void setRightNode(Node rightNode){
    this.rightNode = rightNode;
  }

  public Node getLeftNode(){
    return this.leftNode;
  }

  public Node getRightNode(){
    return this.rightNode;
  }
  
  // Mezgla salīdzināšana pēc to biežuma
  public int compareTo(Node node){
    if(this.weight > node.weight) return 1;
    else if(this.weight < node.weight) return -1;
    else return 0;
  }

}