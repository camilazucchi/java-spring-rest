package br.com.zucchicamila.java_spring_rest.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    public MapperUtil() {
    }

    private static final ModelMapper mapper = new ModelMapper();

    /* Este método é um utilitário genérico para converter um objeto de um tipo (classe de origem) para outro
     * tipo (classe de destino) usando um mapeador.
     * O origin é o objeto de origem que desejamos converter.
     * Class<D> destination é a classe de destino para o qual desejamos converter o objeto de origem. */
    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        // Itera sobre e converte item por item em objeto de destino:
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
