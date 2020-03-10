public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            HBox stack = new HBox();
            VBox buttons = new VBox();
            primaryStage.setTitle("Charts");

            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            final NumberAxis xxAxis = new NumberAxis();
            final NumberAxis yyAxis = new NumberAxis();
            xAxis.setLabel("Numer lini");
            yAxis.setLabel("Ilosc znakow");
            final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
            final AreaChart<Number,Number> areaChart = new AreaChart<Number,Number>(xxAxis,yyAxis);


            String f1 = "files/1.txt";
            String f2 = "files/2.txt";
            String f3 = "files/3.txt";

            XYChart.Series series = new XYChart.Series();
            XYChart.Series series2 = new XYChart.Series();
            XYChart.Series series3 = new XYChart.Series();

            series = createChart(f1);
            series2 = createChart(f2);
            series3 = createChart(f3);

            Button b1 = new Button("B1");

            stack.getChildren().addAll(lineChart, b1);
            lineChart.getData().addAll(series, series2, series3);
            Scene scene = new Scene(stack, 800, 600);




            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    private static XYChart.Series createChart(String fileName) throws FileNotFoundException{
        Reader r = new InputStreamReader(new FileInputStream(fileName));
        Scanner sc = new Scanner(r);
        XYChart.Series series = new XYChart.Series();
        series.setName(fileName);

        int chars = 0;
        List<String> linie = new ArrayList<>();

        while(sc.hasNextLine()){
            linie.add(sc.nextLine().replaceAll(" ", ""));
        }
        for(int i = 0; i < linie.size(); i++){
            chars = linie.get(i).length();
            series.getData().add(new XYChart.Data(i, chars));
        }
        sc.close();

        return series;
    }

    public static void main(String[] args) {
        launch(args);
    }
}