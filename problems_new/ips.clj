(ns problems.ips)

(defn classify4 [s]
  (let [ipv4re #"^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$"
        ipv4re2 #"\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\.|$)){4}\b"
        ipv6re #"^(?:[0-9a-fA-F]{1,4}\:){7}[0-9a-fA-F]{1,4}$"]
    (cond 
      (re-matches ipv4re2 s) "IPv4"
      (re-matches ipv6re s) "IPv6"
      :else "Neither")))

(defn classify-net [s]
  (let [ipv4re #"^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$"
        valid-ipv6 (fn [s]
                     (try
                       (java.net.InetAddress/getByName s)
                       true
                       (catch java.net.UnknownHostException e
                         false)))]
    (cond 
      (re-matches ipv4re s) "IPv4"
      (valid-ipv6 s) "IPv6"
      :else "Neither")))

(defn check-ips [fun ip_array]
  (map fun ip_array))

(check-ips classify ["13.23.3.67",
                     "172.316.254.1",
                     "aaaa:bbbb:cccc:1111:6789:abcd:1011:2022",
                     "21DA:D3:0:2F3B:2AA:FF:FE28:9C5A",
                     "aaaa::1111:6789:abcd:0:0",
                     "21DA::2F3B:2AA:FF:FE28::9C5A",
                     "qweasd"])

(check-ips classify-net ["123.23.3.567",
                         "aaaa:bbbb:cccc:1111:6789:abcd:1011:2022",
                         "21DA:D3:0:2F3B:2AA:FF:FE28:9C5A",
                         "aaaa::1111:6789:abcd:0:0",
                         "21DA::2F3B:2AA:FF:FE28::9C5A",
                         "qweasd"])

(true? (re-matches #"\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\.|$)){4}\b"
                   "192.168.2.1"))



(defn isIPv4Address [inputString]
  (let [[_ q1 q2 q3 q4] (re-matches #"(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})"
                                    inputString)]
    (if (some nil? [q1 q2 q3 q4]) false
        (every? #(< % 256) (map #(Integer/parseInt %) [q1 q2 q3 q4])))))

(isIPv4Address "172.216.254.1")

(isIPv4Address "1172.3316.254.1")

(re-matches #"(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})"
            "1172.3316.254.1")


(map #(Integer/parseInt %) ["123" "435" "0"])


(some nil? [1 2 3 nil 3])

(defn acontains? [arr p]
  (not= (.indexOf arr p) -1))

(defn avoidObstacles [inputArray]
  (let [arr (vec (sort inputArray))
        lst (last arr)]
    (loop [jump 2
           pos 0]
      (let [new-pos (+ pos jump)]
        (cond (> new-pos lst) jump
              (acontains? arr new-pos) (recur (inc jump) 0)
              :else (recur jump new-pos))))))

(avoidObstacles [5, 3, 6, 7, 9])
