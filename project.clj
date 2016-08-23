(defproject matthiasn/systems-toolbox-ui "0.6.1-alpha8"
  :description "UI components for systems-toolbox"
  :url "https://github.com/matthiasn/systems-toolbox"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/cljc" "src/clj" "src/cljs"]

  :dependencies [[reagent "0.6.0-rc"]
                 [matthiasn/systems-toolbox "0.6.1-alpha6"]]

  :profiles
  {:dev {:dependencies   [[org.clojure/clojure "1.9.0-alpha11"]
                           [org.clojure/clojurescript "1.9.225"]
                           [matthiasn/systems-toolbox-sente "0.6.1-alpha7"]
                           [ch.qos.logback/logback-classic "1.1.7"]
                           [hiccup "1.0.5"]
                           [org.seleniumhq.selenium/selenium-java "2.53.1"
                            :exclusions [commons-io]]
                           [org.seleniumhq.selenium/selenium-api "2.53.1"]
                           [org.seleniumhq.selenium/selenium-server "2.53.1"]
                           [org.seleniumhq.selenium/selenium-remote-driver "2.53.1"]
                           [org.seleniumhq.selenium/selenium-chrome-driver "2.53.1"]
                           [org.seleniumhq.selenium/selenium-htmlunit-driver "2.52.0"]
                           [com.codeborne/phantomjsdriver "1.3.0"]
                           [clj-webdriver "0.7.2" :exclusions [org.clojure/core.cache
                                                               commons-io]]]
          :resource-paths ["test-resources"]}
   :test {:resource-paths ["test-resources"]
          :jvm-opts       ["-Dwebdriver.chrome.driver=bin/chromedriver"]}}

  :plugins [[lein-codox "0.9.6" :exclusions [org.clojure/clojure]]
            [test2junit "1.2.2"]
            [lein-cljsbuild "1.1.4"]]

  :test2junit-output-dir ~(or (System/getenv "CIRCLE_TEST_REPORTS") "target/test2junit")

  :clean-targets ^{:protect false} ["test-resources/public/js/build/" "target/"]

  :aliases {"integration-tests" ["do" "clean" ["cljsbuild" "once" "test"] "test2junit"]}

  :cljsbuild
  {:builds [{:id           "test"
             :source-paths ["src/cljs" "test/cljs/"]
             :figwheel     true
             :compiler     {:main          "example.core"
                            :asset-path    "js/build"
                            :optimizations :advanced
                            :output-to     "test-resources/public/js/build/example.js"}}]})
