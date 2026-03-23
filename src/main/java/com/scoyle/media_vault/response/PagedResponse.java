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

    private long totalRecords;
    private long totalPages;
    private int pageSize;
    private int page;
    private Object results;
}
