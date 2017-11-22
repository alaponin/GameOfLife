public enum Status {

    DEAD {
        @Override
        public String toString() {
            return "D";
        }
    }, ALIVE {
        @Override
        public String toString() {
            return "A";
        }
    };


}
