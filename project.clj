(defproject matthiasn/systems-toolbox-ui "0.5.6"
  :description "UI components for systems-toolbox"
  :url "https://github.com/matthiasn/systems-toolbox"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj" "src/cljs" "src/cljc"]

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [reagent "0.6.0-alpha"]]

  :plugins [[lein-codox "0.9.4" :exclusions [org.clojure/clojure]]
            [lein-cljsbuild "1.1.2"]])
