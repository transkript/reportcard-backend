package com.transkript.reportcard.api.filter;

import com.transkript.reportcard.business.service.i.SchoolSettingsService;
import com.transkript.reportcard.business.util.SettingsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SettingsFilter implements Filter {
    private final SchoolSettingsService schoolSettingsService;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {


        SettingsUtil.writeAllToJson(schoolSettingsService.retrieveAll());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
