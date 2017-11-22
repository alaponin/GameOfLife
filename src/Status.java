public enum Status {

    DEAD {
        @Override
        public String toString() {
            return " ";
        }
    }, ALIVE {
        @Override
        public String toString() {
            return "*";
        }
    }


}
