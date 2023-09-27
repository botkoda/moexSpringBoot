package ru.shagaliev.moex.dao;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.shagaliev.moex.logic.MoexBuild;
import ru.shagaliev.moex.models.Moex;

import java.util.Comparator;
import java.util.List;

@Component
@Getter
@Setter
public class MoexDao {
    private List<Moex> moexList;
//    private final MoexBuild moexBuild;


//    public MoexDao(MoexBuild moexBuild) {
//        this.moexBuild = moexBuild;
//    }

    public List<Moex> index(){
        MoexBuild moexBuild=new MoexBuild();
        moexList = moexBuild.build();
        moexList.sort(Comparator.comparing(Moex::getPoints5).reversed());
        return moexList;
    }

    public Moex show(String ticket){
        return moexList.stream().filter(x->x.getTicket().equals(ticket)).findAny().orElse(null);
    }
}
