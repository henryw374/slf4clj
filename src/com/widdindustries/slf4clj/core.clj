(ns com.widdindustries.slf4clj.core
  (:import [org.slf4j Logger]
           [org.slf4j LoggerFactory]
           [org.slf4j.event Level]
           (org.slf4j.spi LoggingEventBuilder)))

(set! *warn-on-reflection* true)

(defmacro logger []
  `(LoggerFactory/getLogger (str ~*ns*)))

(defn log [^Logger logger ^Level level msg kvs & [throwable]]
  (let [^LoggingEventBuilder builder (.atLevel logger level)
        ^LoggingEventBuilder builder'
        (reduce (fn [^LoggingEventBuilder builder [k v]]
                  (.addKeyValue builder ^String (str k) v))
          (-> builder
              (.setMessage ^String msg))
          kvs)]
    (if throwable
      (-> builder'
          (.setCause ^Throwable throwable)
          (.log))
      (-> builder'
          (.log)))))

(defmacro error
  ([msg kvs & [throwable]] `(log (logger) Level/ERROR ~msg ~kvs ~throwable)))

(defmacro debug
  ([msg kvs & [throwable]] `(let [builder# (-> (.atDebug (logger)))]
                              (log builder# ~msg ~kvs ~throwable))))

(defmacro info
  ([msg kvs & [throwable]] `(let [builder# (-> (.atInfo (logger)))]
                              (log builder# ~msg ~kvs ~throwable))))

(defmacro trace
  ([msg kvs & [throwable]] `(let [builder# (-> (.atTrace (logger)))]
                              (log builder# ~msg ~kvs ~throwable))))

(defmacro warn
  ([msg kvs & [throwable]] `(let [builder# (-> (.atWarn (logger)))]
                              (log builder# ~msg ~kvs ~throwable))))


(comment 
  (error "hello" { :foo "bar"})
  (error "hello" { :foo {:a 1}})
  (-> (logger) (.info "hello"))
  
  )