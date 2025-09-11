class ClockTestDrive {
    public static void main(String[] args) throws Exception {

        Clock c = new Clock();

        c.setTime("1245");
        String tod = c.getTime();
        System.out.println("time: " + tod);
    }
}
