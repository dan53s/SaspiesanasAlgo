// grupas nosaukums:  Monke Squad

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

public class HuffmanTree{
// Izveido Huffman koku un ģenerē Huffman kodu
  public HuffmanTree(){}

  // Izveido Huffman koku
  public void createAndReplace(){

  }

  // Salīdzina mezglus pēc to biežuma
  public void comparator(){

  }
  
  // Sakārto mezglus pēc to biežuma, ar mazākajām vērtībām sākumā
  public void sortList(){

  }

}

public class Compress{

 static int count = 7;
    static int buffer = 0;
    static int length=0;
    ArrayList<Node> nodes  = new ArrayList<>();
    private File compressFile;
    public compress(String path) throws IOException {
        String out;
        compressFile = new File(path);
        
        if(compressFile.isDirectory()){
            out=path+".zip";
        }else {
            String prefix = path.substring(0, path.lastIndexOf("."));
            out= prefix+".zip";
        }
        BufferedOutputStream outputStream= new BufferedOutputStream(new FileOutputStream(out));
        compressFile(compressFile,outputStream);
        outputStream.close();
    }



      public void compressFile(File ptah,BufferedOutputStream bufferedOutputStream) throws IOException {
        if(ptah.isDirectory()){
            String directoryName = ptah.getPath();
            bufferedOutputStream.write(directoryName.length());
            int type=1;
            bufferedOutputStream.write(type);
            for(int a=0;a<directoryName.length();a++){
                char ch = directoryName.charAt(a);
                bufferedOutputStream.write(ch);
                                 
            }
            compress_file(ptah,bufferedOutputStream);
        }else {
            int type=0;
            String directoryName = ptah.getPath();
           // System.out.println(directoryName+"  "+ directoryName.length());
            bufferedOutputStream.write(directoryName.length());
                         bufferedOutputStream.write(type);//type
            for(int a=0;a<directoryName.length();a++){
                char ch = directoryName.charAt(a);
                bufferedOutputStream.write(ch);
                                 
            }
           // System.out.println(directoryName.length() + "   type   "+ type +"   "+ directoryName );
            compress_f(ptah,bufferedOutputStream);
        }
    }

        private void compress_file(File ptah, BufferedOutputStream bufferedOutputStream) throws IOException {
        if (!ptah.exists())
            return;
        File[] files = ptah.listFiles();
         for (int i = 0; i < files.length; i++) {
       compressFile(files[i],bufferedOutputStream);
        }
    }

        private void compress_f(File file, BufferedOutputStream bufferedOutputStream) throws IOException {
        if(!file.exists())
            return;
        int[] characterAndWeight =getInts(file);
        for(int i= 0;i<256;i++){
            if(characterAndWeight[i]!=0){
                nodes.add(new Node(characterAndWeight[i],i));
            }
                 }//Get the weight and node of the Huffman tree
        HashMap<Integer,String> map= new HashMap<>();
        huffmanTree huffmanTree =new huffmanTree(nodes);
        huffmanTree.print(huffmanTree.root,"",  map);//create the hashMap
        writeFile(file,bufferedOutputStream,map);//compress the file
        nodes.clear();
        map.clear();
    }

  // veic saspiestā faila ierakstu
  public void writeFile(File path, BufferedOutputStream bufferedOutputStream, HashMap<Integer,String> map) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(path));
        BufferedOutputStream out = bufferedOutputStream;
        String theCodeOfLength="";
        for(int i=0x80000000;i!=0;i>>>=1){
            theCodeOfLength+=(length&i)==0?'0':'1';
                 }//convert to 32-bit binary
        for (int j = 0; j<32 ; j++){
            char ch = theCodeOfLength.charAt(j);
            writeBit(ch-'0',out);
        }
                 //System.out.println(length+" 32-bit encoding "+ yy);
        length=0;
//write the length of byte into the compressed file
        for(int i =0;i<=255;i++){
            if(map.containsKey(i)){
                String character= map.get(i);
                int a = character.length();
                 out.write((byte)a);
            }else {
                out.write((byte)0);
            }
        }

        for(int i= 0;i<=255;i++){
            if(map.containsKey(i)){
                String character = map.get(i);
                for (int j = 0; j< character.length(); j++) {
                    char ch = character.charAt(j);
                    writeBit(ch-'0',out);
                }
            }
                 }//Write Huffman coding

        //System.out.println(buffer+" the last  code    "+count);
        int  value= fis.read();
        while(value!=-1){
            String str= map.get(value);
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                writeBit(ch-'0',out);
            }
            value = fis.read();
        }
        System.out.println(buffer);
        if(buffer!=0)
                         Out.write(buffer);//write to the end of the file
        buffer=0;
        count=7;
        fis.close();
    }

  // writeFile() palīgmetode bitu ierakstīšanai failā
  public void writeBit(int ch,BufferedOutputStream outputStream)throws IOException{
    int a= ch<<count;
        buffer=buffer|a;
        count--;
        if (count==-1){
            outputStream.write(buffer);
            count=7;
            buffer=0;
        }

  }

 
  // writeFile() palīgmetode
  public  static int[] getInts(File path) throws IOException {
            int [] times = new int[256];
        BufferedInputStream fis = new BufferedInputStream(new  FileInputStream(path));
        int value = fis.read();
        while (value!=-1){
            length++;
            times[value]++;
            value=fis.read();
        }
        fis.close();
        return times;

  }

}


public class Decompress{
// Faila atkodēšana

  public Decompress(){}

  // inicializē faila atkodēšanu, pārliecinās vai fails eksistē, atgriež kļūdu pretējā gadījumā
	public void findFile(){

  }

	// faila atkodēšanas no zip formāta algoritms
	public void decompressFile(){

  }

	// palīgmetode faila lasīšanai
	public void read(){

  }
}



class Main {
   //Galvenā klase programmas kontrolei

    // Faila saspiešana
    public static void compress(){

    }

    // Faila atkodēšana
    public static void decode(){

    }

    // Izvada faila izmēru
    public static void size(){

    }

    //
    public static void equal(){

    }

    // Metode programmas izslēgšanai
    public static void exit(){

    }
}