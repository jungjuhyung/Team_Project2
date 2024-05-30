package com.ict.travel.common;

import org.springframework.stereotype.Component;

@Component
public class MapConverter {
    // Lambert Conformal Conic projection parameters
	double Re = 6371.00877; // 지구 반경 (km)
	double grid = 5.0; // 격자 간격 (km)
	double slat1 = 30.0; // 첫 번째 표준 병렬
	double slat2 = 60.0; // 두 번째 표준 병렬
	double olon = 126.0; // 기준 경도
	double olat = 38.0; // 기준 위도
	double xo = 42; // 가짜 동쪽값
	double yo = 135; // 가짜 북쪽값
	double first = 0; // 초기화 여부

    public double[] mapConv(double lon, double lat, int code) {
        double lon1, lat1, x1, y1;
        lon1 = lon;
        lat1 = lat;

        if (code == 1) { // 위경도 -> (X,Y)
            double[] grid = lamcproj(lon1, lat1, code);
            grid[0] = (int) (grid[0] + 1.5);
            grid[1] = (int) (grid[1] + 1.5);
            return grid;
        } else { // (X,Y) -> 위경도
            double[] latlon = lamcproj(lon1, lat1, code);
            return latlon;
        }
    }

    public double[] lamcproj(double lon, double lat, int code) {
        final double PI = Math.asin(1.0) * 2.0;
        final double DEGRAD = PI / 180.0;
        final double RADDEG = 180.0 / PI;

        double re = Re / grid;
        double slat1 = this.slat1 * DEGRAD;
        double slat2 = this.slat2 * DEGRAD;
        double olon = this.olon * DEGRAD;
        double olat = this.olat * DEGRAD;

        double sn = Math.tan(PI * 0.25 + slat2 * 0.5) / Math.tan(PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        if (code == 1) { // 위경도 -> (X,Y)
            double ra = Math.tan(PI * 0.25 + lat * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lon * DEGRAD - olon;
            if (theta > PI) theta -= 2.0 * PI;
            if (theta < -PI) theta += 2.0 * PI;
            theta *= sn;
            double x = ra * Math.sin(theta) + xo;
            double y = ro - ra * Math.cos(theta) + yo;
            return new double[]{x, y};
        } else { // (X,Y) -> 위경도
            double xn = lon - xo;
            double yn = ro - lat + yo;
            double ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) ra = -ra;
            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - PI * 0.5;
            double theta;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                if (Math.abs(yn) <= 0.0) {
                    theta = PI * 0.5;
                    if (xn < 0.0) theta = -theta;
                } else {
                    theta = Math.atan2(xn, yn);
                }
            }
            double alon = theta / sn + olon;
            double lonRes = alon * RADDEG;
            double latRes = alat * RADDEG;
            return new double[]{lonRes, latRes};
        }
    }
}
