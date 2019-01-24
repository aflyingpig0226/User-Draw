package userDraw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDraw {
    private String startTimeDay;
    private String MDN;
    private double male;
    private double female;
    private double age1;
    private double age2;
    private double age3;
    private double age4;
    private double age5;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(startTimeDay).append("|");
        sb.append(MDN).append("|");
        sb.append(new BigDecimal(male).setScale(3, 4).doubleValue()).append("|");
        sb.append(new BigDecimal(female).setScale(3, 4).doubleValue()).append("|");
        sb.append(new BigDecimal(age1).setScale(3, 4).doubleValue()).append("|");
        sb.append(new BigDecimal(age2).setScale(3, 4).doubleValue()).append("|");
        sb.append(new BigDecimal(age3).setScale(3, 4).doubleValue()).append("|");
        sb.append(new BigDecimal(age4).setScale(3, 4).doubleValue()).append("|");
        sb.append(new BigDecimal(age5).setScale(3, 4).doubleValue()).append("|");
        return sb.toString();
    }

    /**
     * 性别融合
     * @param male2
     * @param female2
     * @param times
     */
    public void protraitSex(double male2, double female2, long times){
        double sum = this.male + this.female + (male2 + female2) * times;
        if (sum!=0){
            this.male = (this.male + male2 + times) / sum;
            this.female = (this.female + female2 + times) / sum;
        }
    }

    /**
     * 年龄融合
     * @param pAge1
     * @param pAge2
     * @param pAge3
     * @param pAge4
     * @param pAge5
     * @param times
     */
    public void protraitAge(double pAge1, double pAge2, double pAge3, double pAge4, double pAge5, long times){
        double sum = (this.age1 + this.age2 + this.age3 + this.age4 + this.age5) + (pAge1 + pAge2 + pAge3 + pAge4 + pAge5) * times;
        if (sum != 0){
            this.age1 = (pAge1 * times + age1) / sum;
            this.age2 = (pAge2 * times + age2) / sum;
            this.age3 = (pAge3 * times + age3) / sum;
            this.age4 = (pAge4 * times + age4) / sum;
            this.age5 = (pAge5 * times + age5) / sum;
        }
    }

    /**
     * 初始化性别
     * @param male
     * @param female
     */
    public void initSex(float male, float female){
        float sum = male + female;
        if (sum != 0){
            this.male = male / sum;
            this.female = female / sum;
        }
    }

    /**
     * 初始化年龄
     * @param pAge1
     * @param pAge2
     * @param pAge3
     * @param pAge4
     * @param pAge5
     */
    public void initAge(float pAge1, float pAge2, float pAge3, float pAge4, float pAge5){
        float sum = pAge1 + pAge2 + pAge3 + pAge4 + pAge5;
        if(sum != 0){
            this.age1 = pAge1 / sum;
            this.age2 = pAge2 / sum;
            this.age3 = pAge3 / sum;
            this.age4 = pAge4 / sum;
            this.age5 = pAge5 / sum;
        }
    }
}
