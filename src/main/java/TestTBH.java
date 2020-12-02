import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;
//org.openxmlformats.schemas.drawingml.x2006.chart
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

/**
 * https://www.csdn.net/gather_2a/MtjaYgysNzQwNjQtYmxvZwO0O0OO0O0O.html
 * 操作设置公式，操作函数，生成图表
 */
public class TestTBH {
    public static void main(String[] args) throws IOException, InvalidFormatException {
       // XSSFClientAnchor
           //     HSSFPatriarch
        //ClientAnchor
       // new File("c:\\1.xls");
        Workbook wb = WorkbookFactory.create(new File("C:\\Users\\yssz\\Desktop\\1.xlsx").toURI().toURL().openStream());
        Sheet sheetAt = wb.getSheetAt(0);
        List<? extends PictureData> allPictures = wb.getAllPictures();
       // HSSFPatriarch drawingPatriarch = (HSSFPatriarch) sheetAt.getDrawingPatriarch();
        XSSFDrawing drawingPatriarch1 = (XSSFDrawing) sheetAt.getDrawingPatriarch();
        //用来移动单元格位置
        List<XSSFShape> shapes = drawingPatriarch1.getShapes();

        //获取chart设置图表的取值范围 用来设置图表的数据源取值范围
        List<XSSFChart> charts = drawingPatriarch1.getCharts();
        XSSFChart xssfChart = charts.get(0);

        CTChart ctChart = xssfChart.getCTChart();
        CTPlotArea plotArea = ctChart.getPlotArea();
        //dataRangeMaxValue 代表取值范围单元格行数最大值
        Integer dataRangeMaxValue = 9;
        CTTitle title = ctChart.getTitle();//图表的标题
        List<CTBarChart> barChartList = plotArea.getBarChartList();//4.0 需要jdk1.8
        CTBarChart ctBarChart = barChartList.get(0);
        List<CTBarSer> serList = ctBarChart.getSerList();
        CTBarSer ctBarSer = serList.get(0);
        // 设置横坐标区
        CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
        CTStrRef ctStrRef = cttAxDataSource.addNewStrRef();
        String axisDataRange = new CellRangeAddress(2, 5, 12, 12).formatAsString(sheetAt.getSheetName(), true);
        ctStrRef.setF(axisDataRange);
        // 数据区域
        CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
        CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
        // 选第3-8行,第13列作为数据区域 //1 2 3
        String numDataRange = new CellRangeAddress(2, 5, 13, 15).formatAsString(sheetAt.getSheetName(),
                true);
        ctNumRef.setF(numDataRange);


        //移动图表的逻辑
        for (int i = 0; i < shapes.size(); i++) {
            XSSFShape xssfShape = shapes.get(i);

            XSSFClientAnchor clientAnchor = (XSSFClientAnchor) xssfShape.getAnchor();
           // if(xssfShape instanceof  XSSFPicture){
//                XSSFPicture picture = (XSSFPicture)xssfShape;
//                XSSFPictureData pictureData = picture.getPictureData();
//                XSSFClientAnchor clientAnchor = picture.getClientAnchor();
                int row1 = clientAnchor.getRow1();
                int row2 = clientAnchor.getRow2();
                short col1 = clientAnchor.getCol1();
                short col2 = clientAnchor.getCol2();
                int dx1 = clientAnchor.getDx1();
                int dx2 = clientAnchor.getDx2();
                int dy1 = clientAnchor.getDy1();
                int dy2 = clientAnchor.getDy2();
            clientAnchor.setRow1(10+row1);
             row1 = clientAnchor.getRow1();
            clientAnchor.setRow2(10+row2);
            clientAnchor.setCol1(5+col1);
            clientAnchor.setCol2(5+col2);
             row2 = clientAnchor.getRow2();
             col1 = clientAnchor.getCol1();
             col2 = clientAnchor.getCol2();
            FileOutputStream fos =null;
            File outfile = new File("C:\\Users\\yssz\\Desktop\\3.xlsx");
            fos = new FileOutputStream(outfile);
            wb.write(fos);
                System.out.println("row1: "+row1+" , row2: "+row2+" , col1: "+col1+" , col2: "+col2);
                System.out.println("dx1: "+dx1+" , dx2: "+dx2+" , dy1: "+dy1+" , dy2: "+dy2);
           // }
        }
//        List<HSSFShape> children = drawingPatriarch.getChildren();
//        for (int i = 0; i < children.size(); i++) {
//            HSSFShape  hssfShape =  children.get(i);
//            if(hssfShape instanceof HSSFPicture ){
//                //转换 XSSFPicture  hssfShape instanceof XSSFPicture
//                HSSFPicture picture = (HSSFPicture)hssfShape;
//
//            }
//            HSSFPicture hssfPicturePicture = (HSSFPicture) hssfShape;
//            //获取图片数据  HSSFPicture extends HSSFSimpleShape implements Picture
//                                             //HSSFSimpleShape  HSSFSimpleShape
//            HSSFPictureData pictureData = hssfPicturePicture.getPictureData();
//            HSSFClientAnchor clientAnchor = hssfPicturePicture.getClientAnchor();
//            int row1 = clientAnchor.getRow1();
//            int row2 = clientAnchor.getRow2();
//            short col1 = clientAnchor.getCol1();
//            short col2 = clientAnchor.getCol2();
//            int dx1 = clientAnchor.getDx1();
//            int dx2 = clientAnchor.getDx2();
//            int dy1 = clientAnchor.getDy1();
//            int dy2 = clientAnchor.getDy2();
//            System.out.println("row1: "+row1+" , row2: "+row2+" , col1: "+col1+" , col2: "+col2);
//            System.out.println("dx1: "+dx1+" , dx2: "+dx2+" , dy1: "+dy1+" , dy2: "+dy2);
//        }

    }

}
