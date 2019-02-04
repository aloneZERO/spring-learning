package externals.demo.disc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author justZero
 * @since 2018/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlankDisc implements Disc {

    private String title;

    private String artist;

    public void info() {
        System.out.println("title: "+title+", artist: "+artist);
    }

}
