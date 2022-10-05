package org.example;

import org.example.pojo.Film;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {



        Path filePath = Paths.get("D:/Projects/CSV_reader/src/main/resources/movies.csv");
        Charset charset = StandardCharsets.UTF_8;
/*
        List<Film> films=new ArrayList<>();
        List<String> filmsString=new ArrayList<>();


try (Stream<String> lines= Files.readAllLines(filePath, charset).stream() ) {

    for (String line : lines.skip(1).toList()) {
        filmsString.add(line);
        String[] stringValidTokens = line.split(",");
        films.add(Film.builder()
                .name(stringValidTokens[0])
                .rating(new BigDecimal(stringValidTokens[1]))
                .build()
        );
    }

}
        filmsString.stream()
                .skip(1)
                .map(x->x.split(","))
                .forEach(x-> System.out.println(x[0] + " , " + x[1]));


       Map<String,BigDecimal> map= new HashMap<>();

           map=   filmsString.stream()
                .map(x->x.split(","))
                .collect(Collectors.toMap(
                        x->x[0],
                        x->new BigDecimal(x[1])
                ));
*/





        List<Film> films3;
        //filmString
           films3=Files.readAllLines(filePath, charset).stream()
                   .skip(1)
                   .map(x->x.split(","))
                   .map(x->Film.builder()
                           .name(x[0])
                           .rating(new BigDecimal(x[1]))
                           .build())
                   .collect(Collectors.toList());

        films3.forEach(System.out::println);


        Optional<Film> worstRated=films3.stream().min(Comparator.comparing(Film::getRating));
        Optional<Film> bestRated=films3.stream().max(Comparator.comparing(Film::getRating));


        Optional<BigDecimal> sum = Optional.of(films3.stream()
                .map(Film::getRating)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add)
                .divide(new BigDecimal(films3.size()),1, RoundingMode.HALF_UP));

         System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");
        sum.ifPresent(System.out::println);
        worstRated.ifPresent(System.out::println);
        bestRated.ifPresent(System.out::println);








           /*

        Film worstRated= Film.builder().rating(new BigDecimal("100")).build();
        Film bestRated=Film.builder().rating(BigDecimal.ZERO).build();
        BigDecimal avgRating=BigDecimal.ZERO;


        for (Film film : films) {
            System.out.println(film.toString());

            //neporovnavat k -1 0 1 vede k moznym false negatives
            if(film.getRating().compareTo(worstRated.getRating()) <0){
                worstRated=film;
            }
            if (film.getRating().compareTo(bestRated.getRating()) > 0) {
                bestRated=film;
            }
            avgRating=avgRating.add(film.getRating());
        }


        // precision vcetne cifer pred desetinnou carkou,  kdy budu mit avg jednociferny po deleni tak se veme extra desetinna hodnota, ya desetinnou carkou je scale
        //avgRating=avgRating.divide(new BigDecimal(films.size()),new MathContext(3)).setScale(1, RoundingMode.HALF_UP);

        avgRating=avgRating.divide(new BigDecimal(films.size()),RoundingMode.HALF_UP);



        System.out.println("Nejhůře hodnocený:" + worstRated);
        System.out.println("Nejlépe hodnocený:" + bestRated);
        System.out.println("Průměrné hodnocení:" + avgRating);
*/
    }
}