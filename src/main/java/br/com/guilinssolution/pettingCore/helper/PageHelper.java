package br.com.guilinssolution.pettingCore.helper;

import br.com.guilinssolution.pettingCore.model.dto.util.PageDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class PageHelper {

    private static final int MAXLIMIT_LITE = 200;
    private static final int MAXLIMIT = 20;
    private static final int MINLIMIT = 1;

    public static Pageable getPage(PageDTO page) {
        log.debug("Criando paginação dos dados...");
        return getPageable(page, MAXLIMIT);
    }

    public static Pageable getPageLite(PageDTO page) {
        log.debug("Criando paginação dos dados LITE...");
        return getPageable(page, MAXLIMIT_LITE);
    }

    private static Pageable getPageable(final PageDTO page, final int maxLimit ) {

        int thisLimit = maxLimit;
        int thisOffSet = 0;

        if(page != null) {
            thisLimit = page.getLimit();
            thisOffSet = page.getOffset();
        }

        if (thisOffSet < 0) {
            thisOffSet = 0;
        }
        if (thisLimit > maxLimit || thisLimit < MINLIMIT) {
            thisLimit = maxLimit;
        }

        return PageRequest.of(thisOffSet, thisLimit);
    }
}