// Compiled by ClojureScript 1.7.228 {}
goog.provide('wonderblog.bannerlinks_magic');
goog.require('cljs.core');
goog.require('clojure.string');
goog.require('wonderblog.util');
goog.require('wonderblog.data');
wonderblog.bannerlinks_magic.get_normal_size = cljs.core.memoize.call(null,(function (){
return ((wonderblog.data.magnify_size_as_percent * wonderblog.util.get_banner_height.call(null)) / wonderblog.data.magnify_factor);
}));
wonderblog.bannerlinks_magic.perform_resize_transition = (function wonderblog$bannerlinks_magic$perform_resize_transition(var_args){
var args__7509__auto__ = [];
var len__7502__auto___7632 = arguments.length;
var i__7503__auto___7633 = (0);
while(true){
if((i__7503__auto___7633 < len__7502__auto___7632)){
args__7509__auto__.push((arguments[i__7503__auto___7633]));

var G__7634 = (i__7503__auto___7633 + (1));
i__7503__auto___7633 = G__7634;
continue;
} else {
}
break;
}

var argseq__7510__auto__ = ((((2) < args__7509__auto__.length))?(new cljs.core.IndexedSeq(args__7509__auto__.slice((2)),(0))):null);
return wonderblog.bannerlinks_magic.perform_resize_transition.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),argseq__7510__auto__);
});

wonderblog.bannerlinks_magic.perform_resize_transition.cljs$core$IFn$_invoke$arity$variadic = (function (d3elem,target_size,p__7630){
var vec__7631 = p__7630;
var skip_transition_QMARK_ = cljs.core.nth.call(null,vec__7631,(0),null);
var starting_size = wonderblog.util.get_size.call(null,d3elem);
var size_dx = (starting_size - target_size);
var left_target = (wonderblog.util.get_style_property_dimension.call(null,d3elem,"left") - (size_dx / (-2)));
if(cljs.core.truth_(skip_transition_QMARK_)){
return d3elem.style("width",[cljs.core.str(target_size),cljs.core.str("px")].join('')).style("height",[cljs.core.str(target_size),cljs.core.str("px")].join('')).style("left",[cljs.core.str(left_target),cljs.core.str("px")].join(''));
} else {
return d3elem.transition().duration((250)).style("width",[cljs.core.str(target_size),cljs.core.str("px")].join('')).style("height",[cljs.core.str(target_size),cljs.core.str("px")].join('')).style("left",[cljs.core.str(left_target),cljs.core.str("px")].join(''));
}
});

wonderblog.bannerlinks_magic.perform_resize_transition.cljs$lang$maxFixedArity = (2);

wonderblog.bannerlinks_magic.perform_resize_transition.cljs$lang$applyTo = (function (seq7627){
var G__7628 = cljs.core.first.call(null,seq7627);
var seq7627__$1 = cljs.core.next.call(null,seq7627);
var G__7629 = cljs.core.first.call(null,seq7627__$1);
var seq7627__$2 = cljs.core.next.call(null,seq7627__$1);
return wonderblog.bannerlinks_magic.perform_resize_transition.cljs$core$IFn$_invoke$arity$variadic(G__7628,G__7629,seq7627__$2);
});
wonderblog.bannerlinks_magic.magnify_selected_BANG_ = (function wonderblog$bannerlinks_magic$magnify_selected_BANG_(){
var normal_size = wonderblog.bannerlinks_magic.get_normal_size.call(null);
var large_size = (normal_size * wonderblog.data.magnify_factor);
return wonderblog.bannerlinks_magic.perform_resize_transition.call(null,wonderblog.util.get_current_link.call(null),large_size,true);
});
wonderblog.bannerlinks_magic.wire_hover_BANG_ = (function wonderblog$bannerlinks_magic$wire_hover_BANG_(){
var normal_size = wonderblog.bannerlinks_magic.get_normal_size.call(null);
return wonderblog.util.get_all_links.call(null).on("mouseenter",((function (normal_size){
return (function (e){
var thiz = this;
if(cljs.core.not.call(null,wonderblog.util.currently_selected_QMARK_.call(null,thiz))){
var d3this = d3.select(thiz);
var hover_size = (normal_size * wonderblog.data.hover_magnify_factor);
return wonderblog.bannerlinks_magic.perform_resize_transition.call(null,d3this,hover_size);
} else {
return null;
}
});})(normal_size))
).on("mouseleave",((function (normal_size){
return (function (e){
var thiz = this;
if(cljs.core.not.call(null,wonderblog.util.currently_selected_QMARK_.call(null,thiz))){
var d3this = d3.select(thiz);
return wonderblog.bannerlinks_magic.perform_resize_transition.call(null,d3this,normal_size);
} else {
return null;
}
});})(normal_size))
);
});
wonderblog.bannerlinks_magic.get_first_link = (function wonderblog$bannerlinks_magic$get_first_link(){
return d3.select(".navlink-0");
});
wonderblog.bannerlinks_magic.get_second_link = (function wonderblog$bannerlinks_magic$get_second_link(){
return d3.select(".navlink-1");
});
wonderblog.bannerlinks_magic.get_last_link = (function wonderblog$bannerlinks_magic$get_last_link(){
return d3.select(".navlink-2");
});
wonderblog.bannerlinks_magic.get_left_val = (function wonderblog$bannerlinks_magic$get_left_val(d3element){
return wonderblog.util.get_style_property_dimension.call(null,d3element,"left");
});
wonderblog.bannerlinks_magic.get_right_val = (function wonderblog$bannerlinks_magic$get_right_val(d3element){
return (wonderblog.bannerlinks_magic.get_left_val.call(null,d3element) + wonderblog.util.get_size.call(null,d3element));
});
wonderblog.bannerlinks_magic.reposition_middle_link_BANG_ = (function wonderblog$bannerlinks_magic$reposition_middle_link_BANG_(){
var or__6444__auto__ = cljs.core._EQ_.call(null,wonderblog.util.get_current_link.call(null).node(),wonderblog.bannerlinks_magic.get_second_link.call(null).node());
if(or__6444__auto__){
return or__6444__auto__;
} else {
var new_left_val = (((wonderblog.bannerlinks_magic.get_right_val.call(null,wonderblog.bannerlinks_magic.get_first_link.call(null)) + wonderblog.bannerlinks_magic.get_left_val.call(null,wonderblog.bannerlinks_magic.get_last_link.call(null))) / (2)) - (wonderblog.bannerlinks_magic.get_normal_size.call(null) / (2)));
return wonderblog.util.set_style_property_dimension.call(null,wonderblog.bannerlinks_magic.get_second_link.call(null),"left",new_left_val);
}
});
wonderblog.bannerlinks_magic.resize_bannerlinks_BANG_ = (function wonderblog$bannerlinks_magic$resize_bannerlinks_BANG_(){
var new_normal = [cljs.core.str(wonderblog.bannerlinks_magic.get_normal_size.call(null)),cljs.core.str("px")].join('');
return cljs.core.dorun.call(null,cljs.core.map.call(null,((function (new_normal){
return (function (bannerlink){
return d3.select(bannerlink).style("width",new_normal).style("height",new_normal);
});})(new_normal))
,(wonderblog.util.get_all_links.call(null)[(0)])));
});
wonderblog.bannerlinks_magic.reposition_bannerlinks_BANG_ = (function wonderblog$bannerlinks_magic$reposition_bannerlinks_BANG_(){
var position_data = wonderblog.util.get_page_key.call(null).call(null,wonderblog.data.left_positions);
return cljs.core.dorun.call(null,cljs.core.map.call(null,((function (position_data){
return (function (bannerlink,new_left_val){
return d3.select(bannerlink).style("left",[cljs.core.str(new_left_val),cljs.core.str("px")].join(''));
});})(position_data))
,(wonderblog.util.get_all_links.call(null)[(0)]),position_data));
});
wonderblog.bannerlinks_magic.onload = (function wonderblog$bannerlinks_magic$onload(){
if(cljs.core.not.call(null,wonderblog.util.mobile_sized_display_QMARK_.call(null))){
wonderblog.bannerlinks_magic.resize_bannerlinks_BANG_.call(null);

wonderblog.bannerlinks_magic.wire_hover_BANG_.call(null);

if(cljs.core.truth_(wonderblog.util.at_main_page_QMARK_.call(null))){
wonderblog.bannerlinks_magic.reposition_bannerlinks_BANG_.call(null);

return wonderblog.bannerlinks_magic.magnify_selected_BANG_.call(null);
} else {
return null;
}
} else {
return null;
}
});
jQuery(wonderblog.bannerlinks_magic.onload);

//# sourceMappingURL=bannerlinks_magic.js.map