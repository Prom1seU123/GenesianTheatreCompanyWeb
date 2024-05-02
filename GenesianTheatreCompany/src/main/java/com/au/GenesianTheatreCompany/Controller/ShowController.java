package com.au.GenesianTheatreCompany.Controller;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.DTO.ShowSearchResult;
import com.au.GenesianTheatreCompany.entity.Show;
import com.au.GenesianTheatreCompany.service.LoggingService;
import com.au.GenesianTheatreCompany.service.ShowService;
import com.au.GenesianTheatreCompany.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public Result list() {
        return showService.listAll();
    }
    @PostMapping("/save")
    public boolean save(@RequestBody Show show, @RequestParam Long uid) {
        String logMessage = String.format("%s adds the new production: %s",
                userService.getEmailByUid(uid),
                show.getPname());
        loggingService.writeLog(logMessage);
        return showService.save(show);
    }

    //modify
    @PostMapping("/mod")
    public boolean mod(@RequestBody Show show, @RequestParam Long uid) {
        String logMessage = String.format("%s updates the production: %s",
                userService.getEmailByUid(uid),
                show.getPname());
        loggingService.writeLog(logMessage);
        return showService.updateById(show);
    }
    //add or modify
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Show show, @RequestParam Long uid) {
        String logMessage = String.format("%s saves or modifies the production: %s",
                userService.getEmailByUid(uid),
                show.getPname());
        loggingService.writeLog(logMessage);
        return showService.saveOrUpdate(show);
    }
    //delete
    @GetMapping("/delete")
    public boolean delete(@RequestParam Long pid, @RequestParam Long uid) {
        String logMessage = String.format("%s deletes the production: %s",
                userService.getEmailByUid(uid),
                showService.getPnameByPid(pid));
        loggingService.writeLog(logMessage);
        return showService.removeById(pid);
    }

    //fuzzy search
    @GetMapping("/fSearch")
    public Result fSearch(@RequestParam(required = false) String kw, @RequestParam(required = false) Integer year) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        boolean hasKeyword = kw != null && !kw.trim().isEmpty();
        boolean hasYear = year != null;
        // Start constructing the query only if at least one condition is present
        if (hasKeyword || hasYear) {
            lambdaQueryWrapper.and(wrapper -> {
                // Initialize a condition block
                if (hasKeyword) {
                    // Keyword conditions are added first
                    wrapper.nested(i -> i
                            .apply("LOWER(pname) LIKE LOWER({0})", "%" + kw.trim() + "%")
                            .or()
                            .apply("LOWER(subtitle) LIKE LOWER({0})", "%" + kw.trim() + "%")
                            .or()
                            .apply("LOWER(productions) LIKE LOWER({0})", "%" + kw.trim() + "%")
                            .or()
                            .apply("LOWER(casts) LIKE LOWER({0})", "%" + kw.trim() + "%")
                            .or()
                            .apply("LOWER(crews) LIKE LOWER({0})", "%" + kw.trim() + "%")
                    );
                }
                // Add the year condition within the same AND clause
                if (hasYear) {
                    if (hasKeyword) {
                        // Add AND only if keyword was added
                        wrapper.and(i -> i.apply("EXTRACT(YEAR FROM startdate) = {0}", year));
                    } else {
                        // Directly apply the year condition if no keyword condition was added
                        wrapper.apply("EXTRACT(YEAR FROM startdate) = {0}", year);
                    }
                }
            });
        }

        // Execute the query and process the results
        List<Show> shows = showService.list(lambdaQueryWrapper);
        List<ShowSearchResult> showSearchResults = shows.stream()
                .map(show -> new ShowSearchResult(show.getPid(), show.getPname(), show.getSubtitle(), show.getStartdate()))
                .collect(Collectors.toList());
        return Result.suc(showSearchResults);
    }



    //precise search
    @PostMapping("/pSearch")
    public List<Show> pSearch(@RequestBody Show show) {
        LambdaQueryWrapper<Show> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Show::getPname, show.getPname());
        return showService.list(lambdaQueryWrapper);
    }

    @GetMapping("/previousShow")
    public Result findAllDistinctYears() {
        return showService.findAllDistinctYears();
    }

    @GetMapping("/previousShow/{year}")
    public Result findShowsByStartYear(@PathVariable("year") int year) {
        return showService.findShowsByStartYear(year);
    }

    @GetMapping("/detail/{pid}")
    public Result findShowDetailByPid(@PathVariable("pid") Long pid) {
        return showService.findShowDetailByPid(pid);
    }


}
