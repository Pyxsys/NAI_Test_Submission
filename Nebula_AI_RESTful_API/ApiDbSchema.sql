create table `ADMIN` (
    `id` int not null auto_increment,
    `name` varchar(5) not null,

    primary key (`id`)
);

create table `APILOG` (
    `event_id` int not null auto_increment,             --to ensure uniqueness among calls--
    `admin_id` int not null,                            --foreign key to identify caller--
    `call_date` date not null default (current_date),   --date, defaulted to time at insert--
    `call_time` time not null default (current_time),   --time, defaulted to time at insert--
    `result` varchar(7) not null,                       --String to display result of api call--
    `error_count` int not null,                         --number of errors in a failed call--

    primary key (`event_id`),
    foreign key (`admin_id`) references `ADMIN`(`id`),

    constraint `chk_result` check (                     --limit result contents to 'success', and 'failed'--
        `result` in ('success', 'failed')
    ),
    constraint `chk_errors_match_result` check (                --ensure consistency between error count and result--
        ((`error_count` = 0) and (`result` in ('success'))) or
        ((`error_count` > 0) and (`result` in ('failed')))
    )
);