package com.adelrioj.NextailExercise;

public class Item {

    private final String code;
    private final String name;

    public static ItemBuilder itemBuilderFor(String code){
        return new ItemBuilder(code);
    }

    private Item(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "com.adelrioj.NextailExercise.Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static class ItemBuilder {
        private String code;
        private String name;

        public ItemBuilder(String code) {
            this.code = code;
        }

        public ItemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Item build() {
            return new Item(code, name);
        }
    }
}
