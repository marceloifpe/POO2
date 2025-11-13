class SonOfBoo extends Boo {
    public SonOfBoo() {
        super("boo");
    }

     public SonOfBoo(int i) { //1
        super("Fred");
    }

      public SonOfBoo(String s) { //2
        super(42);
    }

      public SonOfBoo(int i, String s) { //3
    }

      public SonOfBoo(String a, String b, String c) { //4
        super(a, b);
    }

      public SonOfBoo(int i, int j) { //5
        super("man", j);
    }

      public SonOfBoo(int i, int x, int y) { //6
        super(i, "star");
    }
}