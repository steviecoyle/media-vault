package com.scoyle.media_vault.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse {

    private long count;
    private int resultsPerPage;
    private int page;
    private Object results;
}
