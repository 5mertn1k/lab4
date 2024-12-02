class ComparableNumber implements ComparableType<Integer> {
    final int value;

    public ComparableNumber(int value) {
        this.value = value;
    }

    @Override
    public int compare(Integer object) {
        return Integer.compare(this.value, object);
    }
}