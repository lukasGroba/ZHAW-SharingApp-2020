package ch.zhaw.mas.sharingApp.clientSite.persistence;

public class BackendCommunication <T extends Persistence>{

    final Class<T> typeParameterClass;


    public BackendCommunication(Class<T> typeParameterClass)  {
        this.typeParameterClass = typeParameterClass;
    }

    public T getById(T t){
        return t.getRestTemplate().getForObject(t.getUrl(), typeParameterClass);
    }

}
