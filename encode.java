import java.util.*;

class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n_s = sc.nextInt();
        int len_en_coding_s = sc.nextInt();

        HashMap<String, Character> codewords = new HashMap<String, Character>();
        for(int i = 0; i < n_s; i++){
            char chr_s = sc.next().charAt(0);
            String codeword = sc.next();
            codewords.put(codeword, chr_s);
        }

        String encoded = sc.next();
        String unencoded = new String();
        String word = new String();
        for(int i = 0; i < encoded.length(); i++){
            word += encoded.charAt(i);
            if(codewords.containsKey(word)){
                unencoded += codewords.get(word);
                word = new String();
            }
        }
        System.out.println(unencoded);
    }
}
