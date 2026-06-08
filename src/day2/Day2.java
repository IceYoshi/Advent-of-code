package day2;

import common.Day;


/**
 *
 * @author permi
 */
public class Day2 extends Day {

    private int totalArea = 0;
    private int totalRibbon = 0;

    public Day2() {
        super(FileType.Input);

        String presents[] = input.split("\n");

        for (String present : presents) {
            String dim[] = present.split("x");
            if (dim.length == 3) {
                int l = Integer.parseInt(dim[0]);
                int w = Integer.parseInt(dim[1]);
                int h = Integer.parseInt(dim[2]);
                       
                totalArea += getAreaOfWrappingPaper(l, w, h);
                totalRibbon += getLengthOfRibbon(l, w, h);
            } else {
                System.out.println("Invalid number of dimension parameters");
            }
        }
        
        System.out.println("Total wrapping paper area: " + totalArea);
        System.out.println("Total length of ribbon: " + totalRibbon);
    }

    private int getAreaOfWrappingPaper(int l, int w, int h) {        
        int side1 = l*w;
        int side2 = l*h;
        int side3 = w*h;
        
        return 2*(side1 + side2 + side3) + Math.min(side1, Math.min(side2, side3));
    }
    
    private int getLengthOfRibbon(int l, int w, int h) {
        int length = 2* Math.min(l+w, Math.min(l+h, w+h));
        int bow = l * w * h;
        
        return length + bow;
    }
}
