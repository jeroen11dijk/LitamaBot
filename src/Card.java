enum Card {
    RABBIT("rabbit", Color.BLUE, new Offset[]{new Offset(-1, -1), new Offset(1, 1), new Offset(2, 0)}),
    MONKEY("monkey", Color.BLUE, new Offset[]{new Offset(-1, -1), new Offset(-1, 1), new Offset(1, -1), new Offset(1, 1)}),
    BOAR("boar", Color.RED, new Offset[]{new Offset(-1, 0), new Offset(1, 0), new Offset(0, 1)}),
    GOOSE("goose", Color.BLUE, new Offset[]{new Offset(-1, 0), new Offset(-1, 1), new Offset(1, 0), new Offset(1, -1)}),
    COBRA("cobra", Color.RED, new Offset[]{new Offset(-1, 0), new Offset(1, 1), new Offset(1, -1)}),
    CRAB("crab", Color.BLUE, new Offset[]{new Offset(0, 1), new Offset(-2, 0), new Offset(2, 0)}),
    HORSE("horse", Color.RED, new Offset[]{new Offset(0, 1), new Offset(-1, 0), new Offset(0, -1)}),
    DRAGON("dragon", Color.RED, new Offset[]{new Offset(-1, -1), new Offset(1, -1), new Offset(-2, 1), new Offset(2, 1)}),
    ROOSTER("rooster", Color.RED, new Offset[]{new Offset(-1, 0), new Offset(1, 0), new Offset(1, 1), new Offset(-1, -1)}),
    CRANE("crane", Color.BLUE, new Offset[]{new Offset(-1, -1), new Offset(1, -1), new Offset(0, 1)}),
    ELEPHANT("elephant", Color.RED, new Offset[]{new Offset(-1, 0), new Offset(1, 0), new Offset(-1, 1), new Offset(1, 1)}),
    MANTIS("mantis", Color.RED, new Offset[]{new Offset(-1, 1), new Offset(1, 1), new Offset(0, -1)}),
    TIGER("tiger", Color.BLUE, new Offset[]{new Offset(0, 2), new Offset(0, -1)}),
    FROG("frog", Color.RED, new Offset[]{new Offset(-1, 1), new Offset(1, -1), new Offset(-2, 0)}),
    OX("ox", Color.BLUE, new Offset[]{new Offset(0, 1), new Offset(0, -1), new Offset(1, 0)}),
    EEL("eel", Color.BLUE, new Offset[]{new Offset(1, 0), new Offset(-1, 1), new Offset(-1, -1)});

    public String name;
    public Color color;
    public Offset[] offsets;

    Card(String name, Color color, Offset[] offsets) {
        this.name = name;
        this.color = color;
        this.offsets = offsets;
    }
}

