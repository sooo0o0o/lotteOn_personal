package kr.co.lotteOn.dto.faq;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqPageResponseDTO {


    private List<FaqDTO> dtoList;

    private String cate1;
    private String cate2;
    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    //search
    private String searchType;
    private String keyword;

    //추가필드
    private Map<String, List<FaqDTO>> groupedByCate2;

    @Builder
    public FaqPageResponseDTO(FaqPageRequestDTO pageRequestDTO, List<FaqDTO> dtoList, int total,  Map<String, List<FaqDTO>> groupedByCate2) {
        this.cate1 = pageRequestDTO.getCate1();
        this.cate2 = pageRequestDTO.getCate2();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;
        this.groupedByCate2 = groupedByCate2;

        //search
        this.searchType = pageRequestDTO.getSearchType();
        this.keyword = pageRequestDTO.getKeyword();

        this.startNo = total - ((pg - 1) * size);
        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int)(Math.ceil(total / (double)size));
        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}

