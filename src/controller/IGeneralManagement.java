package controller;

import java.util.List;

public interface IGeneralManagement<T> {
    void addNew(T t);

    void deleteInfo(int index);

    void updateInfo(int index, T t);

    void displayAll();

    List<T> readFile(String path);

    void writeFile(String path);

    int findIndex(String string);
}
