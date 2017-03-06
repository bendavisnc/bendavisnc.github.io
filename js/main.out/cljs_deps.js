goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.object', 'goog.string.StringBuffer', 'goog.array']);
goog.addDependency("../wonderblog/data.js", ['wonderblog.data'], ['cljs.core']);
goog.addDependency("../clojure/string.js", ['clojure.string'], ['goog.string', 'cljs.core', 'goog.string.StringBuffer']);
goog.addDependency("../wonderblog/util.js", ['wonderblog.util'], ['cljs.core', 'clojure.string']);
goog.addDependency("../wonderblog/bannerlinks_magic.js", ['wonderblog.bannerlinks_magic'], ['wonderblog.data', 'wonderblog.util', 'cljs.core', 'clojure.string']);
goog.addDependency("../wonderblog/core.js", ['wonderblog.core'], ['cljs.core', 'wonderblog.bannerlinks_magic']);
goog.addDependency("../boot/cljs/main1380.js", ['boot.cljs.main1380'], ['wonderblog.core', 'cljs.core']);
