package org.example;

import org.example.pojo.Film;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)  {



        Path filePath = Paths.get("D:/Projects/CSV_reader/src/main/resources/movies.csv");
        Charset charset = StandardCharsets.UTF_8;

        List<Film> films=new ArrayList<>();

        // kdy pracovat jen se stringy a nedelat struktury
        /*
        try {
            List<String> lines= Files.readAllLines(filePath, charset);
           for(String line: lines.subList(1, lines.size())) {
                String[] stringValidTokens = line.split(",");
               films.add(Film.builder()
                       .name(stringValidTokens[0])
                       .rating(new BigDecimal(stringValidTokens[1]))
                       .build()
               );
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
*/

try (Stream<String> lines= Files.readAllLines(filePath, charset).stream(); ){
            for(String line: lines.skip(1).toList()) {
                String[] stringValidTokens = line.split(",");
               films.add(Film.builder()
                       .name(stringValidTokens[0])
                       .rating(new BigDecimal(stringValidTokens[1]))
                       .build()
               );
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }










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

    }
}