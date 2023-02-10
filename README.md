# slf4clj

Log data, not strings - with SLF4J

See [the blog post](https://widdindustries.com/blog/logging-data) here for more info

## Usage 


```clojure
(require '[com.widdindustries.slf4clj.core :as log])

(log/info "request-for-help" {"urgency" "high", "caller" "Commissioner Gordon", "foe" "Joker", "target" "Gotham"})
```

Copyright © 2023 [Widd Industries](https://widdindustries.com/about/)


