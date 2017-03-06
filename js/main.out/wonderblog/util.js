// Compiled by ClojureScript 1.7.228 {}
goog.provide('wonderblog.util');
goog.require('cljs.core');
goog.require('clojure.string');
wonderblog.util.get_page_name = cljs.core.memoize.call(null,(function (){
return cljs.core.last.call(null,clojure.string.split.call(null,((window["location"])["pathname"]),"/"));
}));
wonderblog.util.get_page_key = cljs.core.memoize.call(null,(function (){
return cljs.core.keyword.call(null,cljs.core.first.call(null,clojure.string.split.call(null,wonderblog.util.get_page_name.call(null),".")));
}));
wonderblog.util.currently_selected_QMARK_ = (function wonderblog$util$currently_selected_QMARK_(e){
return cljs.core._EQ_.call(null,wonderblog.util.get_page_name.call(null),d3.select(e).attr("href"));
});
wonderblog.util.get_all_links = (function wonderblog$util$get_all_links(){
return d3.selectAll(".navlink");
});
wonderblog.util.get_current_link = (function wonderblog$util$get_current_link(){
return wonderblog.util.get_all_links.call(null).filter((function (e){
var thiz = this;
return wonderblog.util.currently_selected_QMARK_.call(null,thiz);
}));
});
wonderblog.util.at_main_page_QMARK_ = (function wonderblog$util$at_main_page_QMARK_(){
return cljs.core.not.call(null,wonderblog.util.get_current_link.call(null).empty());
});
wonderblog.util.get_pixels = (function wonderblog$util$get_pixels(s){
return (cljs.core.first.call(null,clojure.string.split.call(null,s,"px")) | (0));
});
wonderblog.util.get_size = (function wonderblog$util$get_size(e){
return wonderblog.util.get_pixels.call(null,e.style("width"));
});
wonderblog.util.set_style_property_dimension = (function wonderblog$util$set_style_property_dimension(d3element,property,dim){
return d3element.style(property,[cljs.core.str(dim),cljs.core.str("px")].join(''));
});
wonderblog.util.get_style_property_dimension = (function wonderblog$util$get_style_property_dimension(d3element,property){
return wonderblog.util.get_pixels.call(null,d3element.style(property));
});
wonderblog.util.get_banner_height = (function wonderblog$util$get_banner_height(){
return (d3.select(".banner").node()["offsetHeight"]);
});
wonderblog.util.mobile_sized_display_QMARK_ = (function wonderblog$util$mobile_sized_display_QMARK_(){
return ((d3.select(".banner").node()["offsetWidth"]) <= (1199));
});

//# sourceMappingURL=util.js.map