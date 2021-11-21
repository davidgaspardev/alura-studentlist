package dev.davidgaspar.studentlist.helper;

public interface Repository<T extends Model> {
    void save(T data);
    void edit(T data);
    boolean alreadyExist(T data);
}
