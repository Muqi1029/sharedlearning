//package com.muqi.backendsl.strategy.context;
//
//
//
//import com.muqi.backendsl.enums.SearchModeEnum;
//import com.muqi.backendsl.strategy.SearchStrategy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.List;
//import java.util.Map;
//
//import static com.muqi.backendsl.enums.SearchModeEnum.getStrategy;
//public class SearchStrategyContext
//{
//    /**
//     * 搜索模式
//     */
//    @Value("${search.mode}")
//    private String searchMode;
//
//    @Autowired
//    private Map<String, SearchStrategy> searchStrategyMap;
//
//    /**
//     * 执行搜索策略
//     *
//     * @param keywords 关键字
//     * @return {@link List<ArticleSearchDTO>} 搜索文章
//     */
//    public List<ArticleSearchDTO> executeSearchStrategy(String keywords) {
//        return searchStrategyMap.get(SearchModeEnum.getStrategy(searchMode)).searchArticle(keywords);
//    }
//
//}
