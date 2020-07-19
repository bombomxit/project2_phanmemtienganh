package Controller.process;

public class LongestCommonSubstring {
    public static void main(String[] args) {
//        String str1 = "abcdefgijkl";
//        String str2 = "mnopabgijkw";
//        System.out.println(getLongestCommonSubstring(str1,str2));
        int a[]= {1,5,3};
        System.out.println(a[2]);
    }

    public static String getLongestCommonSubstring(String str1, String str2) {
        //Note this longest[][] is a standard auxialry memory space used in Dynamic
        //programming approach to save results of subproblems.
        //These results are then used to calculate the results for bigger problems
        int[][] longest = new int[str2.length() + 1][str1.length() + 1];
        int min_index = 0, max_index = 0;

        //When one string is of zero length, then longest common substring length is 0
        for(int idx = 0; idx < str1.length() + 1; idx++) {
            longest[0][idx] = 0;
        }

        for(int idx = 0; idx < str2.length() + 1; idx++) {
            longest[idx][0] = 0;
        }

        for(int i = 0; i <  str2.length(); i++) {
            for(int j = 0; j < str1.length(); j++) {

                int tmp_min = j, tmp_max = j, tmp_offset = 0;

                if(str2.charAt(i) == str1.charAt(j)) {
                    //Find length of longest common substring ending at i/j
                    while(tmp_offset <= i && tmp_offset <= j &&
                            str2.charAt(i - tmp_offset) == str1.charAt(j - tmp_offset)) {

                        tmp_min--;
                        tmp_offset++;

                    }
                }
                //tmp_min will at this moment contain either < i,j value or the index that does not match
                //So increment it to the index that matches.
                tmp_min++;

                //Length of longest common substring ending at i/j
                int length = tmp_max - tmp_min + 1;
                //Find the longest between S(i-1,j), S(i-1,j-1), S(i, j-1)
                int tmp_max_length = Math.max(longest[i][j], Math.max(longest[i+1][j], longest[i][j+1]));

                if(length > tmp_max_length) {
                    min_index = tmp_min;
                    max_index = tmp_max;
                    longest[i+1][j+1] = length;
                } else {
                    longest[i+1][j+1] = tmp_max_length;
                }


            }
        }

        return str1.substring(min_index, max_index >= str1.length() - 1 ? str1.length() - 1 : max_index + 1);
    }
}

