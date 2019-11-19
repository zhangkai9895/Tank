package object;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

public class Game extends JPanel{
    private Image OffScreenImage;
   static Image wallImage= new ImageIcon(Game.class.getResource("/img/steel.gif")).getImage();
    //坦克可移动的区域的大小
    private final static int screenWidth=680;
    private final static int screenHeight=680;

    //定义一个map图像上的标志
    final static  int BLANK=-1;
    final static int WALLS=-2;
    final static int STEELS=-3;

    //每一个坦克或者墙砖的大小，单位方格的大小
     final static int width=40;
     final static int height=40;

     //存储所有的墙体的信息
    static ConcurrentHashMap<Integer, Wall> walls=new ConcurrentHashMap<>();
static int [][]map;




    public void initMap()
    {
       int x=screenWidth/Game.width;
       int y=screenHeight/Game.height-1;
       map=new int[y][x];
       //i表示的是坐标y，j代表的是坐标x，分别表示的是行和列
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (i==0||i==y-1||j==0||j==x-1)
                {
                    map[i][j]=STEELS;
                    Wall wall=new Wall(new Coord(j,i),STEELS);
                    walls.put(wall.hashCode(),wall);
                }else {
                    map[j][i]=BLANK;
                }
            }
        }
    }
    /*
    * 打印二维地图数组
    * */

    static void printMap()
    {
        System.out.println("------------------------------start----------------------------");
        for (int[] row :
                map) {
            for (int view:row) {
                System.out.println(view+" ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------end--------------------------------");
    }

    public Game()
    {
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setBounds(0,0,screenWidth,screenHeight);
        setLayout(null);
        initMap();
        new Thread(new Draw()).start();
    }


    class  Draw implements Runnable{
        @Override
        public void run() {
            while (true)
            {
                repaint();
                try {
                    Thread.sleep(10);
                }catch (InterruptedException o)
                {
                    o.printStackTrace();
                }
            }
        }
    }

    synchronized public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;//

        for (Wall wall : walls.values()) {
            g2.drawImage(wallImage, wall.x, wall.y, width, height, null);
        }
         }


   /* synchronized public void update(Graphics g){
        super.update(g);
        if (OffScreenImage == null)
            OffScreenImage = this.createImage(screenWidth, screenHeight);
        Graphics goffscrenn = OffScreenImage.getGraphics();    //设置一个内存画笔颜色为前景图片颜色
        Color c = goffscrenn.getColor();    //还是先保存前景颜色
        goffscrenn.setColor(Color.BLACK);    //设置内存画笔颜色为绿色
        goffscrenn.fillRect(0, 0, screenWidth, screenHeight);    //画成图片，大小为游戏大小
        goffscrenn.setColor(c);    //还原颜色
        g.drawImage(OffScreenImage, 0, 0, null);    //在界面画出保存的图片
        paint(goffscrenn);    //把内存画笔调用给paint

    }*/
}
